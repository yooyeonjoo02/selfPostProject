// import React from "react";
// import "./App.css";
// import Header from "./components/Header";
//
// function App() {
//     return (
//         <div className="App">
//             <Header />
//             {/* 나머지 컴포넌트 추가 */}
//         </div>
//     );
// }
//
// export default App;

import React from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Banner from "./components/Banner";
import InfoSection from "./components/InfoSection";
import PhotoSection from "./components/PhotoSection";
import "./App.css";

function App() {
    return (
        <div className="App">
            <Header />
            <Banner />
            <InfoSection />
            <PhotoSection />
            <Footer /> {/* 푸터를 가장 하단에 추가 */}
        </div>
    );
}

export default App;
