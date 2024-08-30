import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../App";
import axios from "axios";

const LoginForm = () => {
  const [formData, setFormData] = useState({ customerID: "" });
  const { setUser } = useContext(UserContext);
  const navigate = useNavigate(); //initializing useNavigation

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmitCustomer = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get(
        `http://localhost:8080/customer/${formData.customerID}`,
        formData
      );
      console.log("Login Successful:", response.data);
      setUser(response.data);
      setFormData({customerID:""});
      navigate("/dashboard");
    } catch (error) {
      console.error("Something went wrong:", error);
    }
  };

  return (
    <form className="create" onSubmit={handleSubmitCustomer}>
      <h3>Login</h3>
      <label htmlFor="customerId">Customer ID</label>
      <input
        type="text"
        id="customerId"
        name="customerID"
        value={formData.customerID}
        onChange={handleChange}
        required
      />
      <button type="submit">Login</button>
    </form>
  );
};

export default LoginForm;