import React from "react";
import "./Header.css";

function Header() {
    return (
        <header className="header">
            <div className="header-left">
                <button className="menu-button">
                    <img src="https://img.icons8.com/ios-filled/50/000000/menu.png" alt="Menu" />
                </button>
                <h1 className="logo">
                    Codesign <span className="logo-subtitle">Architecture</span>
                </h1>
            </div>
            <nav className="header-middle">
                <ul className="nav-links">
                    <li>Introduction</li>
                    <li>PAGES</li>
                    <li>PORTFOLIO</li>
                    <li>SERVICES</li>
                    <li>BLOG</li>
                    <li>CONTACT US</li>
                </ul>
            </nav>
            <div className="header-right">
                <button className="mail-button">
                    <img src="https://img.icons8.com/ios-filled/50/000000/email.png" alt="Mail" />
                </button>
            </div>
        </header>
    );
}

export default Header;

