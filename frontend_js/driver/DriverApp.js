const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

class DriverApp extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            drivers: [],
            selectedDriverId: 0
        };

        this.selectDriver = this.selectDriver.bind(this);
    }

    selectDriver(event) {
        console.log('selected driver id: ' + event.target.value);

        this.setState({selectedDriverId: event.target.value});
    }

    componentDidMount() {
        axios.get('api/driver/all').then(response => {
            this.setState({drivers: response.data});
        });
    }

    render() {
        return (
            <div className="container-fixed demo-grid">
                <div className="row">
                    <div className="col-l-4 demo-col" >
                        ROUTE
                    </div>
                    <div className="col-l-4 demo-col">
                        ADD INFO
                    </div>
                    <div className="col-l-4 demo-col">
                        <DriverList drivers={this.state.drivers}
                                    selectedDriverId={this.state.selectedDriverId}
                                    selectDriver={this.selectDriver} />
                    </div>
                </div>
            </div>
        )
    }
}

class DriverList extends React.Component{
    render() {
        const drivers = this.props.drivers.map(driver =>
            <Driver key={driver.id} driver={driver}/>
        );

        let select;
        if (this.props.selectedDriverId == 0) {
            select = <select defaultValue={0} onChange={this.props.selectDriver}>
                        <option value={0} disabled>Choose Driver</option>
                        {drivers}
                    </select>
        } else {
            select = <select value={this.props.selectedDriverId} onChange={this.props.selectDriver}>
                        {drivers}
                    </select>
        }

        return (
            <div>
                {select}
            </div>
        )
    }
}

class Driver extends React.Component{
    render() {
        return (
            <option value={this.props.driver.id}>
                {this.props.driver.firstName} {this.props.driver.lastName}
                ({this.props.driver.location.country})
            </option>
        )
    }
}

ReactDOM.render(
    <DriverApp/>,
    document.getElementById('driver_app')
);