const React = require('react');
const axios = require('axios');

import {calculateTruckRoute, clearTrucks, markTruck, optimizeDeliveryRoute} from './deliveryEditorMap';

import Drivers from "./Drivers";

class Trucks extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            trucks: [],
            orderWeight: 0,
            travelTime: 0
        };
    }

    componentDidMount() {
        // TODO: Customize max travel hours, which is 2 in milliseconds (7 200 000) by default
        axios.get('api/truck/all/' + this.props.depotId + '/' + 7200000).then(response => {
            this.setState({trucks: response.data});
        });
    }

    componentDidUpdate(oldProps) {
        if (this.props.depotId !== oldProps.depotId) {
            axios.get('api/truck/all/' + this.props.depotId + '/' + 7200000).then(response => {
                this.setState({trucks: response.data, orderWeight: 0, travelTime: 0});
            });
        } else if (this.props.orderWeight !== oldProps.orderWeight || this.props.travelTime !== oldProps.travelTime) {
            this.setState({orderWeight: this.props.orderWeight, travelTime: this.props.travelTime});
        }
    }

    render() {
        if (this.state.trucks) {
            return (
                <TruckList trucks={this.state.trucks} selectTruck={this.props.selectTruck}
                           addDriver={this.props.addDriver} removeDriver={this.props.removeDriver}
                           deselectAllDrivers={this.props.deselectAllDrivers}
                           orderWeight={this.state.orderWeight} travelTime={this.state.travelTime}/>
            )
        }
    }

}

class TruckList extends React.Component{
    constructor() {
        super();
        this.state = {
            selectedTruck: {},
            truckTransferTime: 0
        };

        this.calculateRouteAndSelectTruck = this.calculateRouteAndSelectTruck.bind(this);
    }

    componentDidUpdate(oldProps) {
        if (this.props.trucks !== oldProps.trucks) {
             this.setState({selectedTruck: {}});
        }
    }

    calculateRouteAndSelectTruck(newTruck) {
        clearTrucks();
        markTruck(newTruck);
        calculateTruckRoute().then(time => {
            this.props.selectTruck(newTruck, parseFloat(this.props.travelTime) + parseFloat(time));
            this.setState({selectedTruck: newTruck, truckTransferTime: time})
        });
    }

    render() {
        const trucks = this.props.trucks.map(truck =>
            <Truck key={truck.id} truck={truck}
                   orderWeight={this.props.orderWeight}
                   calculateRouteAndSelectTruck={this.calculateRouteAndSelectTruck} />
        );

        let wh = parseFloat(this.state.truckTransferTime) + parseFloat(this.props.travelTime);
        let drivers;
        if (this.state.selectedTruck.location) {
            drivers = <Drivers city={this.state.selectedTruck.location} workHours={wh}
                               deselectAllDrivers={this.props.deselectAllDrivers}
                               addDriver={this.props.addDriver} removeDriver={this.props.removeDriver}/>
        }

        return (
            <div className="col-l-8">
                <div className="row">

                    <div className="col-l-6">
                        <ul className="content-list">
                            {trucks}
                        </ul>
                    </div>

                    {drivers}

                </div>
            </div>
        )
    }
}

class Truck extends React.Component{
    constructor() {
        super();

        this.handleChecked = this.handleChecked.bind(this);
    }

    handleChecked() {
        this.props.calculateRouteAndSelectTruck(this.props.truck);
    }

    render() {
        const weightRatio = this.props.orderWeight / this.props.truck.capacity;
        let overweight = weightRatio > 1;

        return (
            <li className="media">
                <div className="media-body">
                    <div className="form-radio-set">
                        <label>
                            <input type="radio" disabled={overweight} onChange={this.handleChecked}
                                   name="rb" value="rb1" className="form-radio"/>
                            {this.props.truck.plate} Loaded: {weightRatio}
                        </label>
                    </div>
                    <div className="media-hint">
                        {this.props.truck.capacity}kg {this.props.truck.location.city} {this.props.truck.status}
                    </div>
                </div>
            </li>
        )
    }
}

export default Trucks;
