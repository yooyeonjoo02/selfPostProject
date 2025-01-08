import React from "react";
import "./PhotoSection.css";

function PhotoSection() {
    const photos = [
        { title: "Floor Plan Design", subtitle: "We Design Ideas" },
        { title: "Interior Design", subtitle: "We Design Ideas" },
        { title: "Construction", subtitle: "We Design Ideas" },
    ];

    return (
        <section className="photo-section">
            <h2>What We Provide</h2>
            <div className="photo-cards">
                {photos.map((photo, index) => (
                    <div key={index} className="photo-card">
                        <h3>{photo.title}</h3>
                        <p>{photo.subtitle}</p>
                    </div>
                ))}
            </div>
        </section>
    );
}

export default PhotoSection;
