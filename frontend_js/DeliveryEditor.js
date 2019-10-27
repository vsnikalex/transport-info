const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

import Cargoes from './Cargoes';

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
            selectedDepot: 1
        };

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({selectedDepot: event.target.value});
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
                <div className="col-l-3 ">
                    <Cargoes depotId={this.state.selectedDepot}/>
                </div>
                <div className="col-l-3 ">
                    <ul className="content-list">
                        <li className="media">
                            <div className="media-body">
                                <div className="form-radio-set">
                                    <label>
                                        <input type="radio" name="rb" value="rb1" className="form-radio"/>
                                        YG78923 Loaded: 50%
                                    </label>
                                </div>
                                <div className="media-hint">10 000kg Krakow OK</div>
                            </div>
                        </li>
                    </ul>
                </div>
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