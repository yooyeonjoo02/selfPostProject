import React from "react";
import "./Footer.css";

function Footer() {
    return (
        <footer className="footer">
            <div className="footer-container">
                <div className="footer-left">
                    <h2 className="footer-title">The Palatin</h2>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem
                        maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. Suspendisse
                        cursus faucibus finibus.
                    </p>
                </div>
                <div className="footer-center">
                    <h3>Find us on the map</h3>
                    <div className="footer-map">
                        {/* 예제 맵 이미지 */}
                        <img src="/images/map-placeholder.png" alt="Map" />
                    </div>
                </div>
                <div className="footer-right">
                    <h3>Subscribe to our newsletter</h3>
                    <form className="newsletter-form">
                        <input type="email" placeholder="Your E-mail" className="newsletter-input" />
                        <button type="submit" className="newsletter-button">
                            Subscribe
                        </button>
                    </form>
                </div>
            </div>
            <div className="footer-bottom">
                <p>
                    Copyright ©2025 All rights reserved | This template is made with{" "}
                    <span className="heart">♡</span> by Colorlib
                </p>
            </div>
        </footer>
    );
}

export default Footer;
