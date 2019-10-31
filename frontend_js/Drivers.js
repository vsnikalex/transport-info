const React = require('react');
const axios = require('axios');

class Drivers extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            drivers: [],
            workHours: 0
        };
    }

    componentDidMount() {
        axios.post('api/driver/city', this.props.city).then(response => {
            this.setState({drivers: response.data, workHours: this.props.workHours});
        });
    }

    componentDidUpdate(oldProps) {
        if (this.props.city !== oldProps.city) {

            if (this.props.city.city !== oldProps.city.city) {
                this.props.deselectAllDrivers();
            }

            axios.post('api/driver/city', this.props.city).then(response => {
                this.setState({drivers: response.data});
            });
        } else if (this.props.workHours !== oldProps.workHours) {
            this.setState({workHours: this.props.workHours});
        }
    }

    render() {
        if (this.state.drivers) {
            return (
                <DriverList drivers={this.state.drivers} workHours={this.state.workHours}
                            addDriver={this.props.addDriver} removeDriver={this.props.removeDriver}/>
            )
        }
    }

}

class DriverList extends React.Component{
    render() {
        const drivers = this.props.drivers.map(driver =>
            <Driver key={driver.id} driver={driver} workHours={this.props.workHours}
                    addDriver={this.props.addDriver} removeDriver={this.props.removeDriver}/>
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
            isChecked: false,
            willBeWorkedWithoutDelivery: 0
        };
        this.handleChecked = this.handleChecked.bind(this);
    }

    estimateDeliveryEndDateTime(workHours) {
        // TODO: Customize task start datetime, which is now by default
        let now = new Date();
        let estDeliveryDays = parseFloat(workHours) / 8;
        // 86400000 - milliseconds per day, divided  by 1000 for Java using epoch seconds, not milliseconds
        return (now.getTime() + estDeliveryDays * 86400000) / 1000;
    }

    componentDidMount() {
        let endDateTime = this.estimateDeliveryEndDateTime(this.props.workHours);

        axios.get('api/task/hours/' + this.props.driver.id + '/' + endDateTime).then(response => {
            this.setState({willBeWorkedWithoutDelivery: response.data});
        });
    }

    componentDidUpdate(oldProps) {
        if (this.props.workHours !== oldProps.workHours) {
            let endDateTime = this.estimateDeliveryEndDateTime(this.props.workHours);

            axios.get('api/task/hours/' + this.props.driver.id + '/' + endDateTime).then(response => {
                this.setState({willBeWorkedWithoutDelivery: response.data});
            });
        }
    }

    handleChecked() {
        if (!this.state.isChecked) {
            this.props.addDriver(this.props.driver);
        } else {
            this.props.removeDriver(this.props.driver);
        }

        this.setState({isChecked: !this.state.isChecked});
    }

    render() {
        const willBeWorkedAfterDelivery = parseFloat(this.state.willBeWorkedWithoutDelivery)
                                            + parseFloat(this.props.workHours);
        let inputDisabled = willBeWorkedAfterDelivery > 176;

        return (
            <li className="media">
                <div className="media-body">
                    <div className="form-checkbox-set">
                        <label>
                            <input type="checkbox" disabled={inputDisabled} onChange={ this.handleChecked }
                                   name="cb0" value="0" className="form-checkbox"/>
                            {this.props.driver.firstName} {this.props.driver.lastName}
                        </label>
                    </div>
                    <div className="media-hint">
                        {this.props.driver.location.city} Future Work: {willBeWorkedAfterDelivery}
                    </div>
                </div>
            </li>
        )
    }
}

export default Drivers;