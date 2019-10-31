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
            travelTime: 0,

            selectedTruckId: 0,

            selectedDrivers: new Set(),

            validInput: false
        };

        this.selectDepot = this.selectDepot.bind(this);

        this.addCargo = this.addCargo.bind(this);
        this.removeCargo = this.removeCargo.bind(this);

        this.selectTruck = this.selectTruck.bind(this);

        this.addDriver = this.addDriver.bind(this);
        this.removeDriver = this.removeDriver.bind(this);
        this.deselectAllDrivers = this.deselectAllDrivers.bind(this);
    }

    selectDepot(event) {
        clearMapAndTable();

        const getSelectedDepotObj = this.props.depots.find(depot =>
            depot.id == event.target.value
        );
        markDepot(getSelectedDepotObj);

        this.setState({selectedDepotId: event.target.value,
                       selectedCargoes: new Set(), orderWeight: 0, travelTime: 0,
                       selectedTruckId: 0,
                       selectedDrivers: new Set()});
    }

    addCargo(cargo, time) {
        let isSet = this.state.selectedCargoes instanceof Set;
        let set;
        if (isSet) {
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
            travelTime: time
        });
    }

    removeCargo(cargo, time) {
        let set = this.state.selectedCargoes;
        set.delete(cargo.id);

        console.log('selected cargoes: [' + Array.from(set).join(' ') + ']');

        this.setState({
            selectedCargoes: set,
            orderWeight: this.state.orderWeight - cargo.weight,
            travelTime: time
        });
    }

    selectTruck(truck) {
        console.log('selected truck: ' + truck.id);

        this.setState({selectedTruckId: truck.id});
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

    render() {
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
                        <div className="col-l-3 ">
                            <div className="form-input-set">
                                <label htmlFor="selectbox" title="Primäroptionen Auswahlliste">Depot</label>
                                <select value={this.state.selectedDepotId} onChange={this.selectDepot}>
                                    {depots}
                                </select>
                            </div>
                        </div>
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