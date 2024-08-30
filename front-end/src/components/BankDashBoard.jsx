import React, { useContext, useEffect, useState } from 'react';
import axios from 'axios';
import { UserContext } from '../App';

const BankDashBoard = () => {
  const { user } = useContext(UserContext);
  const [userData, setUserData] = useState(() => {
    // Check localStorage for user data when the component mounts
    const savedUserData = localStorage.getItem('userData');
    return savedUserData ? JSON.parse(savedUserData) : null;
  });

  useEffect(() => {
    const fetchUserInfo = async () => {
      if (user && user.id && !userData) { // Fetch only if userData is not already available
        try {
          const response = await axios.get(`http://localhost:8080/customer/${user.id}`);
          console.log("User Found:", response.data);

          // Parse the fullName JSON string
          const parsedFullName = JSON.parse(response.data.fullName).fullName;

          // Store the parsed fullName in userData
          const userData = { ...response.data, fullName: parsedFullName };

          // Save user data to state and localStorage
          setUserData(userData);
          localStorage.setItem('userData', JSON.stringify(userData));
        } catch (error) {
          console.error("Something went wrong:", error);
        }
      }
    };

    fetchUserInfo();
  }, [user, userData]);

  return (
    <div>
      <h1>User Dashboard</h1>
      {userData ? (
        <div>
          <h2>Welcome, {userData.fullName}</h2>
          <p><strong>ID:</strong> {userData.id}</p>
          <h3>Accounts</h3>
          {userData.accounts.length > 0 ? (
            <ul>
              {userData.accounts.map((account, index) => (
               <li key={index}>
               <p><strong>Account Number:</strong> {account.number}</p>
               <p><strong>Account Name:</strong> {account.name}</p>
               <p><strong>Opening Balance:</strong> ${account.openingBalance}</p>
               <p><strong>Current Balance:</strong> ${account.balance}</p>
               <p><strong>Sort Code:</strong> {account.sortCode ? account.sortCode : "N/A"}</p>
               <p><strong>Customer ID:</strong> {account.customer}</p>
               <h4>Transactions</h4>
               {/* {account.transactions.length > 0 ? (
                 <ul>
                   {account.transactions.map((transaction, index) => (
                     <li key={index}>{JSON.stringify(transaction)}</li>
                   ))}
                 </ul>
               ) : (
                 <p>No transactions available.</p>
               )} */}
             </li>

              ))}
            </ul>
          ) : (
            <p>No accounts available.</p>
          )}
        </div>
      ) : (
        <p>Loading user data...</p>
      )}
    </div>
  );
};

export default BankDashBoard;
