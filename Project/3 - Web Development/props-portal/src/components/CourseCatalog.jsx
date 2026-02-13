import {useState} from "react";
import CourseCard from "./CourseCard.jsx";

export default function CourseCatalog() {

    const courses = [
        { id: 1, title: "React Fundamentals", category: "Web Dev", level: "Beginner", rating: 4.7 },
        { id: 2, title: "Advanced Java", category: "Backend", level: "Advanced", rating: 4.3 },
        { id: 3, title: "Python", category: "Data", level: "Intermediate", rating: 4.8 },
        { id: 4, title: "Cloud Computing", category: "DevOps", level: "Advanced", rating: 4.4 },
        { id: 5, title: "CSS & Styling", category: "Design", level: "Beginner", rating: 4.1 },
        { id: 6, title: "Machine Learning", category: "AI", level: "Advanced", rating: 4.6 },
        { id: 7, title: "Project Management", category: "Management", level: "Beginner", rating: 4.5 },
        { id: 8, title: "Software Architecture", category: "CS", level: "Advanced", rating: 4.2 }
    ];

    const [selectedCourse, setSelectedCourse] = useState(null);

    const handleSelect = (course) => {
        setSelectedCourse(course);
    };

    return (
        <div>
            <h2 className="text-center mb-4 fw-bold">Course Catalog</h2>

            <div className="row">
                {courses.map((course) => (
                    <div key={course.id} className="col-md-4 mb-3">
                        <CourseCard
                            title={course.title}
                            category={course.category}
                            level={course.level}
                            rating={course.rating}
                            onSelect={() => handleSelect(course)}
                        />
                    </div>
                ))}
            </div>

            {selectedCourse && (
                <div className="card mt-4 shadow">
                    <div className="card-body">
                        <h4 className="card-title bd fw-bold">Selected Course</h4>
                        <p><strong>Title:</strong> {selectedCourse.title}</p>
                        <p><strong>Category:</strong> {selectedCourse.category}</p>
                        <p><strong>Level:</strong> {selectedCourse.level}</p>
                        <p><strong>Rating:</strong> {selectedCourse.rating}</p>
                    </div>
                </div>
            )}
        </div>
    );
}