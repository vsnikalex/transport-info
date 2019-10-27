const React = require('react');
const axios = require('axios');

class Trucks extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            trucks: [],
            orderWeight: 0
        };
    }

    componentDidMount() {
        // Max travel hours is 2 in milliseconds by default
        axios.get('api/truck/all/' + this.props.depotId + '/' + 7200000).then(response => {
            this.setState({trucks: response.data});
        });
    }

    componentDidUpdate(oldProps) {
        if (this.props.depotId !== oldProps.depotId) {

            axios.get('api/truck/all/' + this.props.depotId + '/' + 7200000).then(response => {
                this.setState({trucks: response.data, orderWeight: 0});
            });

        } else if (this.props.orderWeight !== oldProps.orderWeight) {

            this.setState({orderWeight: this.props.orderWeight});

        }
    }

    render() {
        if (this.state.trucks) {
            return (
                <TruckList trucks={this.state.trucks} orderWeight={this.state.orderWeight}/>
            )
        }
    }

}

class TruckList extends React.Component{
    render() {
        const trucks = this.props.trucks.map(truck =>
            <Truck key={truck.id} truck={truck} orderWeight={this.props.orderWeight}/>
        );
        return (
            <div className="col-l-6">
                <div className="row">
                    <div className="col-l-6">
                        <ul className="content-list">
                            {trucks}
                        </ul>
                    </div>
                    <div className="col-l-6">
                        <ul className="content-list">
                            <li className="media">
                                <div className="media-body">
                                    <div className="form-checkbox-set">
                                        <label>
                                        <input type="checkbox" name="cb0" value="0" className="form-checkbox"/>
                                        Test Testovich
                                        </label>
                                    </div>
                                    <div className="media-hint">
                                        Krakow REST
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}

class Truck extends React.Component{
    render() {
        const weightRatio = this.props.orderWeight / this.props.truck.capacity;
        let inputDisabled = weightRatio > 1;

        return (
            <li className="media">
                <div className="media-body">
                    <div className="form-radio-set">
                        <label>
                            <input type="radio" disabled={inputDisabled} name="rb" value="rb1" className="form-radio"/>
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