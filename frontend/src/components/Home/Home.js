import React, {Component} from "react";
import {Button, Container} from "reactstrap";
import {Link} from "react-router-dom";
import AppNavbar from "../AppNavBar/AppNavbar";

class Home extends Component {

    render() {
        return (
            <div>
                <AppNavbar />
                <Container fluid>
                    <Button color="link"><Link to="/clients">Clients</Link></Button>
                </Container>
            </div>
        );
    }
}

export default Home;
