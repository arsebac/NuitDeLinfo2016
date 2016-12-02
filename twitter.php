<?php
//1 - Settings (please update to math your own)
use Abraham\TwitterOAuth\TwitterOAuth;


//2 - Include @abraham's PHP twitteroauth Library
require_once('twitteroauth/autoload.php');

function getTweets($hashtag, $nbTweets)
{
    $consumer_key = 'Tk3hDynYdqJCu6xwDMM46ILtZ'; //Provide your application consumer key
    $consumer_secret = 'dEAYaYVCE0jdi6pTTjbIYtndST3dWTvf6EZTxHZ1qK4rSSTRmT'; //Provide your application consumer secret
    $oauth_token = '804421631263133696-oD6XIO2iEFwAo1SuHj1K7O0Pq6oribS'; //Provide your oAuth Token
    $oauth_token_secret = 'evD2BGdvYfqXLw2Kah0PZU4u4HU0v0eBWdYx5muxTsymS'; //Provide your oAuth Token Secret
//3 - Authentication
    /* Create a TwitterOauth object with consumer/user tokens. */
    $connection = new TwitterOAuth($consumer_key, $consumer_secret, $oauth_token, $oauth_token_secret);

//4 - Start Querying
    $content = $connection->get("search/tweets", ["q" => $hashtag, "count" => $nbTweets]); // récupère les 100 derniers tweets avec le hashtag #nuitinfo
//var_dump($content);
    $array = json_decode(json_encode($content), true);
    $arrayResult = array();
    for ($i = 0; $i < $nbTweets; $i++) {
        $text = $array["statuses"][$i]["text"];
        $name = $array["statuses"][$i]["user"]["name"];
        array_push($arrayResult, array("text" => $text,
            "name" => $name,
            "geo" => $array["statuses"][$i]["geo"]));
        printTweet($name, $text);
    }
    //var_dump($arrayResult);
    $arrayJson = json_encode($arrayResult);
    return $arrayJson;
//$tweet = $connection->post("statuses/update", ["status" => "L'API Twitter marche enfin ! #nuitinfo"]); // tweet un message
}

function printTweet($user, $text){
    echo "User : ".$user."<br>";
    echo "Status : ".$text."<br>";
    echo "<br> <br>";
}

?>