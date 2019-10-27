const React = require('react');
const axios = require('axios');

class Trucks extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            trucks: []
        };
    }

    componentDidMount() {
        axios.get('api/truck/all/' + this.props.depotId).then(response => {
            this.setState({trucks: response.data});
        });
    }

    componentDidUpdate(oldProps) {
        if (this.props.depotId !== oldProps.depotId) {

            axios.get('api/truck/all/' + this.props.depotId).then(response => {
                this.setState({cargoes: response.data});
            });

        }
    }

    render() {
        if (this.state.cargoes) {

            return (
                <CargoList cargoes={this.state.cargoes}/>
            )

        }
    }

}

class CargoList extends React.Component{
    render() {
        const cargoes = this.props.cargoes.map(cargo =>
            <Cargo key={cargo.id} cargo={cargo}/>
        );
        return (
            <ul className="content-list">
                {cargoes}
            </ul>
        )
    }
}

class Cargo extends React.Component{
    render() {
        return (
            <li className="media">
                <div className="media-body">
                    <div className="form-checkbox-set">
                        <label>
                            <input type="checkbox" name="cb0" value="0" className="form-checkbox"/>
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