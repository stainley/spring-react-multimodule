import {render, screen} from "@testing-library/react";
import Home from "../Home";

import {MemoryRouter} from 'react-router-dom';

describe('Home Component Test', () => {

    it('should have AppNavbar', function () {
        render(<MemoryRouter><Home/></MemoryRouter>)

        expect(screen.getByText('Clients')).toBeInTheDocument();
    });
});
