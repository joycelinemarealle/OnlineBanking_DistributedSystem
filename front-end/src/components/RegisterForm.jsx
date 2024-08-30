import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const RegisterForm = () => {
    const [formData, setFormData] = useState({
        fullName: ""
    });

  const navigate = useNavigate(); //initializing useNavigation

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmitCustomer = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/customer",
        formData
      );
      console.log("User Created:", response.data);
      setFormData({
        fullName: ""
      });
      navigate("/login")
    } catch (error) {
      console.error("Something went wrong:", error);
    }
  };

  return (
    <form className="create" onSubmit={handleSubmitCustomer}>
      <h3>Register</h3>

      <label htmlFor="fullName">Full Name</label>
      <input
        type="text"
        id="fullName"
        name="fullName"
        value={formData.fullName}
        onChange={handleChange}
        required
      />
      <button type="submit">Register</button>
    </form>
  );
};

export default RegisterForm;