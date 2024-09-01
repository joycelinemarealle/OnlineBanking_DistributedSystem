import React from 'react';

const AccountInfo = ({ customerId, accounts, onDeleteAccount }) => {
  // Filter out accounts with a balance of 0
  const visibleAccounts = accounts.filter(account => account.balance > 0);

  return (
    <div className="max-w-4xl mx-auto mt-10">
      {visibleAccounts.length > 0 ? (
        <ul className="space-y-4">
          {visibleAccounts.map((account) => (
            <li key={account.number} className="border-b border-gray-200 p-4 m-4 w-full bg-white shadow-lg rounded-lg">
              <p className="text-xl font-semibold text-gray-700">Account Name: <span className="font-normal">{account.name}</span></p>
              <p className="text-xl font-semibold text-gray-700">Opening Balance: <span className="font-normal">${account.openingBalance?.toFixed(2)}</span></p>
              <p className="text-xl font-semibold text-gray-700">Current Balance: <span className="font-normal">${account.balance?.toFixed(2)}</span></p>
              <button
                onClick={() => onDeleteAccount(account.number)}
                className="mt-2 px-4 py-2 bg-red-600 text-white font-medium rounded-md shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500"
              >
                Delete Account
              </button>
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
