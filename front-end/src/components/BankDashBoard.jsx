import React, { useContext, useEffect, useState } from 'react';
import axios from 'axios';
import { UserContext } from '../App';

const BankDashBoard = () => {
  const { user } = useContext(UserContext);
  const [userData, setUserData] = useState(null);

  useEffect(() => {
    const fetchUserInfo = async () => {
      if (user && user.id) {
        try {
          const response = await axios.get(`http://localhost:8080/customer/${user.id}`);
          console.log("User Found:", response.data);

          const parsedFullName = JSON.parse(response.data.fullName).fullName;
          const userData = { ...response.data, fullName: parsedFullName };

          setUserData(userData);
        } catch (error) {
          console.error("Something went wrong:", error);
        }
      }
    };

    fetchUserInfo();
  }, [user]);

  return (
    <div>
      <h1>User Dashboard</h1>
      {userData ? (
        <div>
          <h2>Welcome, {userData.fullName}</h2>
          <p><strong>ID:</strong> {userData.id}</p>
          <h3>Accounts</h3>
          {userData.accounts && userData.accounts.length > 0 ? (
            <ul>
              {userData.accounts.map((account, index) => (
                <li key={index}>
                  <p><strong>Account Number:</strong> {account.number}</p>
                  <p><strong>Account Name:</strong> {account.name}</p>
                  <p><strong>Opening Balance:</strong> ${account.openingBalance ? account.openingBalance.toFixed(2) : "N/A"}</p>
                  <p><strong>Current Balance:</strong> ${account.balance ? account.balance.toFixed(2) : "N/A"}</p>
                  <p><strong>Sort Code:</strong> {account.sortCode ? account.sortCode : "N/A"}</p>
                  <p><strong>Customer ID:</strong> {account.customer}</p>
                  <h4>Transactions</h4>
                  {account.transactions && account.transactions.length > 0 ? (
                    <ul>
                      {account.transactions.map((transaction, index) => (
                        <li key={index}>
                          <p><strong>Time:</strong> {new Date(transaction.time).toLocaleString()}</p>
                          <p><strong>Type:</strong> {transaction.type}</p>
                          <p><strong>Amount:</strong> ${transaction.amount ? transaction.amount : "N/A"}</p>
                          {transaction.fromAccount && (
                            <p><strong>From Account:</strong> {transaction.fromAccount}</p>
                          )}
                          {transaction.toAccount && (
                            <p><strong>To Account:</strong> {transaction.toAccount}</p>
                          )}
                        </li>
                      ))}
                    </ul>
                  ) : (
                    <p>No transactions available.</p>
                  )}
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
