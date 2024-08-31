import React, { useContext, useEffect, useState } from 'react';
import axios from 'axios';
import { UserContext } from '../App';
import AccountInfo from './AccountInfo';
import CreateAccountModal from './CreateAccountModal';

const BankDashBoard = () => {
  const { user } = useContext(UserContext);
  const [userData, setUserData] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchUserInfo = async () => {
      if (user && user.id) {
        try {
          const response = await axios.get(`http://localhost:8080/customer/${user.id}`);
          console.log("User Found:", response.data);

          const parsedFullName = JSON.parse(response.data.fullName).fullName;
          const userData = { ...response.data, fullName: parsedFullName };

          // Fetch accounts for the user
          const accountsResponse = await axios.get(`http://localhost:8080/account?customerId=${user.id}`);
          const filteredAccounts = accountsResponse.data;

          setUserData({ ...userData, accounts: filteredAccounts });
        } catch (error) {
          console.error("Something went wrong:", error);
        }
      }
    };

    fetchUserInfo();
  }, [user]);

  const handleAccountCreated = async () => {
    if (userData) {
      try {
        const response = await axios.get(`http://localhost:8080/account?customerId=${userData.id}`);
        const filteredAccounts = response.data;
        setUserData({ ...userData, accounts: filteredAccounts });
      } catch (error) {
        console.error('Error fetching accounts:', error);
      }
    }
  };

  return (
    <div className="bg-gray-100 w-full relative">
      <div className="max-w-7xl">
        <h1 className="text-3xl font-semibold mb-6 text-gray-800">
          Welcome Back, {userData?.fullName || 'User'}
        </h1>
        
        <button
          onClick={() => setIsModalOpen(true)}
          className="px-4 py-2 bg-indigo-600 text-white font-medium rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 mb-6"
        >
          Create New Account
        </button>
        
        {userData ? (
          <div>
            <div className="mb-6">
              <p className="text-lg font-medium text-gray-600">Your Accounts</p>
              
              <AccountInfo customerId={userData.id} accounts={userData.accounts || []} />
            </div>
          </div>
        ) : (
          <p className="text-gray-500">Loading user data...</p>
        )}
      </div>
      
      {isModalOpen && (
        <>
          <div className="fixed inset-0 bg-black bg-opacity-50 backdrop-blur-sm z-40" />
          <CreateAccountModal 
            isOpen={isModalOpen} 
            onClose={() => setIsModalOpen(false)} 
            customerId={userData?.id} 
            onAccountCreated={handleAccountCreated}
          />
        </>
      )}
    </div>
  );
};

export default BankDashBoard;
