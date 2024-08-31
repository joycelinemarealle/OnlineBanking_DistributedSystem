import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TransactionInfo = ({ accountId }) => {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/transactions/${accountId}`);
        console.log("Transactions Found:", response.data);
        setTransactions(response.data);
      } catch (error) {
        console.error("Something went wrong:", error);
      }
    };

    if (accountId) {
      fetchTransactions();
    }
  }, [accountId]);

  return (
    <div>
      <h4>Transactions</h4>
      {transactions.length > 0 ? (
        <ul>
          {transactions.map((transaction, index) => (
            <li key={index}>
              <p><strong>Time:</strong> {new Date(transaction.time).toLocaleString()}</p>
              <p><strong>Type:</strong> {transaction.type}</p>
              <p><strong>Amount:</strong> ${transaction.amount.toFixed(2)}</p>
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
    </div>
  );
};

export default TransactionInfo;
