import React from 'react';
import { InputMask } from 'react-input-mask';

const serverUrl = 'http://localhost:8080';

export const getAllSuppliers = async () => {
  const response = await fetch(`${serverUrl}/api/suppliers`);
  if (!response.ok) {
    throw new Error('Failed to fetch suppliers');
  }
  return await response.json();
};

export const createSupplier = async (supplier) => {
  // Remove non-numeric characters from CNPJ
  const cleanedSupplier = {
    ...supplier,
    cnpj: supplier.cnpj.replace(/\W/g, '').toUpperCase()
  };

  const response = await fetch(`${serverUrl}/api/suppliers`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(cleanedSupplier),
  });

  if (!response.ok) {
    throw new Error('Failed to create supplier');
  }

  return await response.json();
};

export const CNPJInputMask = (props) => {
  return (
    <InputMask
      mask={['*', '*', '.', '*', '*', '*', '.', '*', '*', '*', '/', '*', '*', '*', '*', '-', /[0-9]/]}
      {...props}
    />
  );
};