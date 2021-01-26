# TASK 1
First task for the BitPanda Android recruitment process

### TODO

1. Show a list of Currency Wallets (Icon, Symbol, balance). As you can see there is a lot of code reuse, 
try to improve it Cryptocoin and Metal are considered as Asset, Fiat and Asset are considered as Currency. 
Each Currency has a Wallet, and you can have multiple Wallets per Currency. 
- For Metals show the name in the list eg.: "Gold" 
- Do not show deleted wallets
- Data should only be retrieved from the Repository

2. Sort the list by type : fiat, cryptocoins, metals and the balance of the wallet
3. Add a functionality to filter Currencies by type (a simple button which rotates the type is enough)
4. If you click on an entry open a simple DetailView where you show the price of the coin
- Use precision to format the price with correct amount of decimal places
- Prices are euro prices

5. Test your implementation with Unit Tests


### GENERAL

* You are free to refactor and improve the given structure. let us know what and why
* Use an architecture pattern of your choice

### NICE TO HAVE 
* You are free to implement also a nice UI 

## AUTO-REVIEW

If I have more time (I had only 2 free evenings for the implementation) I would:

- add an initial data loading (all section should be chosen by default) probably via two-way binding
- improve mapping dtos
- correct package hierarchy
- improve FilterTypesView implementation (would make it more customizable for the future)
- improve SVG URLs fetching (by removing a GlideToVector library which is causing memory leaks and by doing it myself)

I was confused with DummyData, I assumed that I should not change the retrieving data from the repository 
(normally I would replace it with asynchronous functions such as rx Single or Observable). That is why
I have decided to leave the project as an error safe, in case of the asynchronous functions I would handle
potential errors. I was thinking about creating some mock interval fetching data in order to refresh data every few seconds
and potential network state changes but it is not described in the task so I have not implemented it (and that
is why I am passing the whole wallet object to detail view).
I have decided to implement it as a MVVM using data binding because there is a lot of cases where UI can be
easily nested in XML. Also, I think that short view models are ready to read for the others who have not implemented them.




