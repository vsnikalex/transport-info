const React = require('react');
const axios = require('axios');

import {markCargoDestination, removeCargoMarker, optimizeDeliveryRoute} from './deliveryEditorMap';

class Cargoes extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            cargoes: []
        };
    }

    componentDidMount() {
        axios.get('api/cargo/all/' + this.props.depotId).then(response => {
            this.setState({cargoes: response.data});
        });
    }

    componentDidUpdate(oldProps) {
        if (this.props.depotId !== oldProps.depotId) {
            axios.get('api/cargo/all/' + this.props.depotId).then(response => {
                this.setState({cargoes: response.data});
            });
        }
    }

    render() {
        if (this.state.cargoes) {
            return (
                <CargoList cargoes={this.state.cargoes}
                           addCargo={this.props.addCargo} removeCargo={this.props.removeCargo}/>
            )
        }
    }

}

class CargoList extends React.Component{
    render() {
        const cargoes = this.props.cargoes.map(cargo =>
            <Cargo key={cargo.id} cargo={cargo}
                   addCargo={this.props.addCargo} removeCargo={this.props.removeCargo}/>
        );
        return (
            <div className="col-l-4 ">
                <ul className="content-list">
                    {cargoes}
                </ul>
            </div>
        )
    }
}

class Cargo extends React.Component{
    constructor() {
        super();
        this.state = {
            isChecked: false
        };
        this.handleChecked = this.handleChecked.bind(this);
    }

    handleChecked() {
        if (!this.state.isChecked) {
            markCargoDestination(this.props.cargo);

            optimizeDeliveryRoute().then(timeAndActivities => {
                this.props.addCargo(this.props.cargo, timeAndActivities[0], timeAndActivities[1]);
            });
        } else {
            removeCargoMarker(this.props.cargo.id);

            optimizeDeliveryRoute().then(timeAndActivities => {
                this.props.removeCargo(this.props.cargo, timeAndActivities[0], timeAndActivities[1]);
            });
        }

        this.setState({isChecked: !this.state.isChecked});
    }

    render() {
        return (
            <li className="media">
                <div className="media-body">
                    <div className="form-checkbox-set">
                        <label>
                            <input type="checkbox" onChange={this.handleChecked}
                                   name="cb0" value="0" className="form-checkbox"/>
                            {this.props.cargo.description}
                        </label>
                    </div>
                    <div className="media-hint">{this.props.cargo.weight}kg {this.props.cargo.status}</div>
                </div>
            </li>
        )
    }
}

export default Cargoes;