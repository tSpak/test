API endpoints:

	{POST [/customer]} : com.example.test.api.controller.CustomerController#save(Customer)
	{PUT [/customer]} : com.example.test.api.controller.CustomerController#update(Customer)
	{DELETE [/customer]} : com.example.test.api.controller.CustomerController#delete(Customer)	
	{GET [/customer/listByZipCode]} : com.example.test.api.controller.CustomerController#listByZipCode(String)
	{GET [/customer/getByDocumentId]} : com.example.test.api.controller.CustomerController#getByDocumentId(String)
	{POST [/customer/{customerId}/address]} : com.example.test.api.controller.AddressController#save(Integer, Address)
	{DELETE [/customer/{customerId}/address]} : com.example.test.api.controller.AddressController#delete(Integer, Address)
	{GET [/customer/{customerId}/listAddresses]} : com.example.test.api.controller.AddressController#listAddresses(Integer)
	
Postman collection:
	/test/src/main/resources/postman/test.postman_collection.json
	
Postgree database Script:
	/test/src/main/resources/sql/script.txt