const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

import {
    markDepot,
    clearMapAndTable
} from './deliveryEditorMap';

import Cargoes from './Cargoes';
import Trucks from './Trucks';

class DeliveryEditor extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            depots: []
        };
    }

    componentDidMount() {
        axios.get('api/depot/all').then(response => {
            this.setState({depots: response.data});
        });
    }

    render() {
        return (
            <Lists depots={this.state.depots} />
        )
    }
}

class Lists extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            selectedDepotId: 0,

            selectedCargoes: new Set(),
            orderWeight: 0,
            activities: {},
            travelTime: 0,

            selectedTruck: {},

            selectedDrivers: new Set()
        };

        this.selectDepot = this.selectDepot.bind(this);

        this.addCargo = this.addCargo.bind(this);
        this.removeCargo = this.removeCargo.bind(this);

        this.selectTruck = this.selectTruck.bind(this);

        this.addDriver = this.addDriver.bind(this);
        this.removeDriver = this.removeDriver.bind(this);
        this.deselectAllDrivers = this.deselectAllDrivers.bind(this);

        this.sendData = this.sendData.bind(this);
    }

    selectDepot(event) {
        clearMapAndTable();

        const getSelectedDepotObj = this.props.depots.find(depot =>
            depot.id == event.target.value
        );
        markDepot(getSelectedDepotObj);

        this.setState({selectedDepotId: event.target.value,
                       selectedCargoes: new Set(), orderWeight: 0,
                       route: {}, travelTime: 0,
                       selectedTruckId: 0,
                       selectedDrivers: new Set(), inputIsValid: false});
    }

    addCargo(cargo, time, activities) {
        let set;
        if (this.state.selectedCargoes instanceof Set) {
            set = this.state.selectedCargoes;
            set.add(cargo.id);
        } else {
            set = new Set();
            set.add(cargo.id);
        }

        console.log('selected cargoes: [' + Array.from(set).join(' ') + ']');

        this.setState({
            selectedCargoes: set,
            orderWeight: this.state.orderWeight + cargo.weight,
            travelTime: isNaN(time) ? 0 : time,
            activities: activities
        });
    }

    removeCargo(cargo, time, activities) {
        let set = this.state.selectedCargoes;
        set.delete(cargo.id);

        console.log('selected cargoes: [' + Array.from(set).join(' ') + ']');

        this.setState({
            selectedCargoes: set,
            orderWeight: this.state.orderWeight - cargo.weight,
            travelTime: isNaN(time) ? 0 : time,
            activities: activities
        });
    }

    selectTruck(truck) {
        this.setState({selectedTruck: truck});
    }

    addDriver(driver) {
        let isSet = this.state.selectedDrivers instanceof Set;
        let set;
        if (isSet) {
            set = this.state.selectedDrivers;
            set.add(driver.id);
        } else {
            set = new Set();
            set.add(driver.id);
        }

        console.log('selected drivers: [' + Array.from(set).join(' ') + ']');

        this.setState({selectedDrivers: set});
    }

    removeDriver(driver) {
        let set = this.state.selectedDrivers;
        set.delete(driver.id);

        console.log('selected drivers: [' + Array.from(set).join(' ') + ']');

        this.setState({selectedDrivers: set});
    }

    deselectAllDrivers() {
        console.log('deselect all drivers');

        this.setState({selectedDrivers: new Set()});
    }

    sendData() {
        // TODO: pass to API: cargoes, truck, drivers, activities
        console.log('cargoes: ' + this.state.selectedCargoes.size);
        console.log('truck id: ' + this.state.selectedTruck.id);
        console.log('overload: ' + ((this.state.orderWeight / this.state.selectedTruck.capacity) > 1));
        console.log('drivers: ' + this.state.selectedDrivers.size);
        console.log('activities: ' + this.state.activities);
    }

    render() {
        let cargoesOk = this.state.selectedCargoes.size > 0;
        let truckOk = this.state.selectedTruck.id != 0;
        let overload = (this.state.orderWeight / this.state.selectedTruck.capacity) > 1;
        let driversOk = this.state.selectedDrivers.size > 0;
        let inputIsValid = cargoesOk && (truckOk && !overload) && (driversOk);

        let submitButton;
        if (inputIsValid) {
            submitButton = <button type="button" className="btn btn-positive" onClick={this.sendData}>Submit</button>;
        } else {
            submitButton = <button type="button" className="btn btn-default" disabled={true}>Submit</button>;
        }

        const depots = this.props.depots.map(depot =>
            <Depot key={depot.id} depot={depot} />
        );

        if (this.state.selectedDepotId == 0) {
            return (
                <div className="row">
                    <div className="col-l-3 ">
                        <div className="form-input-set">
                            <label htmlFor="selectbox" title="Primäroptionen Auswahlliste">Depot</label>
                            <select onChange={this.selectDepot} defaultValue={0}>
                                <option value={0} disabled>Choose Depot</option>
                                {depots}
                            </select>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div>
                    <div className="row">
                        <div className="col-l-3">
                            <div className="form-input-set">
                                <label htmlFor="selectbox" title="Primäroptionen Auswahlliste">Depot</label>
                                <select value={this.state.selectedDepotId} onChange={this.selectDepot}>
                                    {depots}
                                </select>
                            </div>
                        </div>
                        <div className="col-l-6"></div>
                        <div className="col-l-1"></div>
                        <div className="col-l-1">
                            {submitButton}
                        </div>
                        <div className="col-l-1"></div>
                    </div>
                    <div className="row">
                        <Cargoes depotId={this.state.selectedDepotId}
                                 addCargo={this.addCargo} removeCargo={this.removeCargo}/>
                        <Trucks  depotId={this.state.selectedDepotId} selectTruck={this.selectTruck}
                                 addDriver={this.addDriver} removeDriver={this.removeDriver}
                                 deselectAllDrivers={this.deselectAllDrivers}
                                 orderWeight={this.state.orderWeight} travelTime={this.state.travelTime}/>
                    </div>
                </div>
            );
        }
    }
}

class Depot extends React.Component{
    render() {
        return (
            <option value={this.props.depot.id}>{this.props.depot.location.country} ({this.props.depot.type})</option>
        )
    }
}

ReactDOM.render(
    <DeliveryEditor/>,
    document.getElementById('delivery_editor')
);