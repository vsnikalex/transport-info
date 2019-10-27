const React = require('react');
const axios = require('axios');

class Drivers extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            drivers: []
        };
    }

    componentDidMount() {
        axios.post('api/driver/city', this.props.city).then(response => {
            this.setState({drivers: response.data});
        });
    }

    componentDidUpdate(oldProps) {
        if (this.props.city !== oldProps.city) {

            axios.post('api/driver/city', this.props.city).then(response => {
                this.setState({drivers: response.data});
            });

        }
    }

    render() {
        if (this.state.drivers) {
            return (
                <DriverList drivers={this.state.drivers} />
            )
        }
    }

}

class DriverList extends React.Component{
    render() {
        const drivers = this.props.drivers.map(driver =>
            <Driver key={driver.id} driver={driver} />
        );
        return (
            <div className="col-l-6">
                <ul className="content-list">
                    {drivers}
                </ul>
            </div>
        )
    }
}

class Driver extends React.Component{
    constructor() {
        super();
        this.state = {
            isChecked: false
        };
        this.handleChecked = this.handleChecked.bind(this);
    }

    handleChecked() {
        if (!this.state.isChecked) {
            console.log('CHECKED');
        } else {
            console.log('UNCHECKED');
        }

        this.setState({isChecked: !this.state.isChecked});
    }

    render() {
        return (
            <li className="media">
                <div className="media-body">
                    <div className="form-checkbox-set">
                        <label>
                            <input type="checkbox" onChange={ this.handleChecked }
                                   name="cb0" value="0" className="form-checkbox"/>
                            {this.props.driver.firstName} {this.props.driver.lastName}
                        </label>
                    </div>
                    <div className="media-hint">
                        {this.props.driver.location.city} {this.props.driver.status}
                    </div>
                </div>
            </li>
        )
    }
}

export default Drivers;