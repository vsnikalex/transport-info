const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

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
            selectedDepot: 1,
            orderWeight: 0
        };

        this.handleChange = this.handleChange.bind(this);
        this.changeWeight = this.changeWeight.bind(this);
    }

    handleChange(event) {
        this.setState({selectedDepot: event.target.value, orderWeight: 0});
    }

    changeWeight(newWeight){
        this.setState({orderWeight: this.state.orderWeight + newWeight});
    }

    render() {
        const depots = this.props.depots.map(depot =>
            <Depot key={depot.id} depot={depot} />
        );

        return (
            <div className="row">
                <div className="col-l-3 ">
                    <div className="form-input-set">
                        <label htmlFor="selectbox">Depot</label>
                        <select value={this.state.selectedDepot} onChange={this.handleChange}>
                            {depots}
                        </select>
                    </div>
                </div>
                <Cargoes depotId={this.state.selectedDepot} changeWeight={this.changeWeight}/>
                <Trucks depotId={this.state.selectedDepot} orderWeight={this.state.orderWeight}/>
            </div>
        )
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