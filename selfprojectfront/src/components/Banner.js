import React, { useState, useEffect } from "react";
import "./Banner.css";

function Banner() {
    const images = [
        "/images/Banner1.png",
        "/images/Banner2.png",
    ];

    const [currentIndex, setCurrentIndex] = useState(0);

    // 4초마다 슬라이드 변경
    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
        }, 4000); // 4초마다 슬라이드 변경

        return () => clearInterval(interval); // 컴포넌트 언마운트 시 클린업
    }, [images.length]);

    return (
        <div className="banner">
            <div
                className="banner-slides"
                style={{
                    transform: `translateX(-${currentIndex * 100}%)`, // 현재 슬라이드로 이동
                }}
            >
                {images.map((image, index) => (
                    <div
                        key={index}
                        className="banner-slide"
                        style={{
                            backgroundImage: `url(${image})`,
                        }}
                    >
                        <div className="banner-content">
                            <h2>Modern House Interior New York</h2>
                            <p>Residential Project</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Banner;
