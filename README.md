# Glimpse Warmup

Welcome to the Glimpse warmup project! It is intended to go along with the [Glimpse warmup tutorial](https://github.com/tessellator/glimpse/blob/master/doc/02_warmup.md).

This project differs from that created in the warmup tutorial in one minor way: it uses the [environ](https://github.com/weavejester/environ) library to load the Twitter tokens from the environment instead of putting them directly in the code. This is so we don't accidentally upload Twitter tokens to GitHub!

Here are two things to do to get this project up and running if you intend to deploy it yourself.

1. Create a `.lein-env` file that contains the following, with the obvious substitutions:

   ```
   {:consumer-key        "YOUR-CONSUMER-KEY-HERE"
    :consumer-secret     "YOUR-CONSUMER-SECRET-HERE"
    :access-token        "YOUR-ACCESS-TOKEN-HERE"
    :access-token-secret "YOUR-ACCESS-TOKEN-SECRET-HERE"}
   ```

2. Set the parameters using Heroku CLI

   After you have created a Heroku project, you can use the `config:set` command to set the same values as in the `.lein-env` file:

   `heroku config:set CONSUMER_KEY="YOUR CONSUMER KEY" CONSUMER_SECRET="YOUR CONSUMER SECRET" ...`

After these changes, you should be ready to deploy to Heroku.

Please contact me if you have any issues!
