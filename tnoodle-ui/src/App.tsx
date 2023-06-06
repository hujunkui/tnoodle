import "bootstrap/dist/css/bootstrap.css";
import "./App.css";
import Main from "./main/components/Main";
import SideBar from "./main/components/SideBar";

const App = () => (
    <div className="App container-fluid">
        <div className="row">
            {/*<div className="col-lg-3 bg-dark overflow-auto" id="side-bar">*/}
            {/*    <SideBar />*/}
            {/*</div>*/}
            <div className="col-lg-2 m-0 p-0"></div>
            <div className="col-lg-8 m-0 p-0">
                <Main />
            </div>
            <div className="col-lg-2 m-0 p-0"></div>
        </div>
    </div>
);

export default App;

export const BASE_PATH = process.env.PUBLIC_URL;
