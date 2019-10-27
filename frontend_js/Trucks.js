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
        // Max travel hours is 2 in milliseconds by default
        axios.get('api/truck/all/' + this.props.depotId + '/' + 7200000).then(response => {
            this.setState({trucks: response.data});
        });
    }

    componentDidUpdate(oldProps) {
        if (this.props.depotId !== oldProps.depotId) {

            axios.get('api/truck/all/' + this.props.depotId + '/' + 7200000).then(response => {
                this.setState({trucks: response.data});
            });

        }
    }

    render() {
        if (this.state.trucks) {

            return (
                <TruckList trucks={this.state.trucks}/>
            )

        }
    }

}

class TruckList extends React.Component{
    render() {
        const trucks = this.props.trucks.map(truck =>
            <Truck key={truck.id} truck={truck}/>
        );
        return (
            <ul className="content-list">
                {trucks}
            </ul>
        )
    }
}

class Truck extends React.Component{
    render() {
        return (
            <li className="media">
                <div className="media-body">
                    <div className="form-radio-set">
                        <label>
                            <input type="radio" name="rb" value="rb1" className="form-radio"/>
                            {this.props.truck.plate} Loaded: 0%
                        </label>
                    </div>
                    <div className="media-hint">
                        {this.props.truck.capacity}kg
                        {this.props.truck.location.city}
                        {this.props.truck.status}
                    </div>
                </div>
            </li>
        )
    }
}

export default Trucks;