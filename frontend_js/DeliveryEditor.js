const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

import {clearRoutes, markDepot, clearCargoes, clearDepots} from './deliveryEditorMap';

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
            orderWeight: 0,
            travelTime: 0
        };

        this.selectDepot = this.selectDepot.bind(this);
        this.changeWeight = this.changeWeight.bind(this);
        this.setTravelTime = this.setTravelTime.bind(this);
    }

    selectDepot(event) {
        clearCargoes();
        clearDepots();
        clearRoutes();

        const getSelectedDepotObj = this.props.depots.find(depot =>
            depot.id == event.target.value
        );
        markDepot(getSelectedDepotObj);

        this.setState({selectedDepotId: event.target.value, orderWeight: 0});
    }

    changeWeight(weight){
        this.setState({orderWeight: this.state.orderWeight + weight});
    }

    setTravelTime(time){
        console.log(time);

        this.setState({travelTime: time});
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
                                 changeWeight={this.changeWeight} setTravelTime={this.setTravelTime}/>
                        <Trucks depotId={this.state.selectedDepotId} orderWeight={this.state.orderWeight}/>
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