In our testing, we had trouble with importing our project and running it in Eclipse when we
zipped the project directory. So to combat this, we copied the 'gui' and 'utils' folder
with its contents into our already imported project. That should help with running the program. 
Don't spam the peform trade button as the coingecko api takes time to collect information and it will
cause an error.

To login into the system, the username we used was 'admin' and the password was '12345'. The
brokers and their corresponding information will be stored into a file after a trade is
performed. When performing a trade, if a trade can be performed on 'Strategy A', that same
trade with the same coin cannot be performed on 'Strategy B'. The same goes for 'Strategy C'
and 'Strategy D'. However, if Strategy 'A' can be performed on a coin, either 'Strategy C' or
'Strategy D' can also be performed on the same coin with a different broker. And vice versa.
Strategy A and B trade based on prices while C and D trade based on volume.