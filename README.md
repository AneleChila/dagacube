# dagacube springboot
Slot Game


NOTE1
IF MIGRATIION DOES NOT EXECUTE WITH THE START OF THE APPLICATION,
RUN THE SCRIPT MANUALLY IN DATABASE(MYSQL)



NOTE2
HERE ARE THE API'S FOR THE SOLUTION
WIIT THE EXAMPLE DATA

1. GET : http://localhost:8090/rankinteractive/dagacube/{userId}

2. POST : http://localhost:8090/rankinteractive/dagacube/deduct
   {"playerId" : 1,"transactionId" : 89, "amount" : 3, "promoCode" : "paper"}

3. POST : http://localhost:8090/rankinteractive/dagacube/deposit
   {"playerId" : 1,"transactionId" : 89, "amount" : 3}

4. POST : http://localhost:8090/rankinteractive/dagacube/transactions
   {"username" : "boom","passsword" : "boom"}
