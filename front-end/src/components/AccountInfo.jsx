import React from 'react';

const AccountInfo = ({ customerId, accounts }) => {
  return (
    <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-lg rounded-lg">
      {accounts.length > 0 ? (
        <ul className="space-y-4">
          {accounts.map((account) => (
            <li key={account.number} className="border-b border-gray-200 p-4">
              <p className="text-xl font-semibold text-gray-700">Account Number: <span className="font-normal">{account.number}</span></p>
              <p className="text-xl font-semibold text-gray-700">Account Name: <span className="font-normal">{account.name}</span></p>
              <p className="text-xl font-semibold text-gray-700">Opening Balance: <span className="font-normal">${(account.openingBalance || 0).toFixed(2)}</span></p>
              <p className="text-xl font-semibold text-gray-700">Current Balance: <span className="font-normal">${(account.balance || 0).toFixed(2)}</span></p>
              <p className="text-xl font-semibold text-gray-700">Sort Code: <span className="font-normal">{account.sortCode || "N/A"}</span></p>
            </li>
          ))}
        </ul>
      ) : (
        <p className="text-center text-gray-500 text-lg">No accounts available.</p>
      )}
    </div>
  );
};

export default AccountInfo;
