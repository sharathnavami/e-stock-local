db.company.drop();
db.company.insertMany([
  {
    _id: 1,
    companyName: 'Tensor'
  },
  {
    _id: 2,
    companyName: 'Flow'
  }
]);

db.stocks.drop();
db.stocks.insertMany([
  {
    _id: 1,
    companyId: 'Tensor',
	stockPrice: '33.45'
  },
  {
    _id: 2,
    companyId: 'Flow',
	stockPrice: '109.45'
  }
]);