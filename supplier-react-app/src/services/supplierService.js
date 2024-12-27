const serverUrl = 'http://localhost:8081';

export const getAllSuppliers = async () => {
  const response = await fetch(serverUrl+'/api/suppliers');
  if (!response.ok) {
    throw new Error('Failed to fetch suppliers');
  }
  return await response.json();
};

export const createSupplier = async (supplier) => {
  const cleanedSupplier = {
    ...supplier,
    cnpj: supplier.cnpj.replace(/\W/g, '').toUpperCase().split('').map(char => char.charCodeAt(0) - 48).join('')
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