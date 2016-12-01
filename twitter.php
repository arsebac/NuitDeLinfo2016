<?php
//1 - Settings (please update to math your own)
use Abraham\TwitterOAuth\TwitterOAuth;

$consumer_key='Tk3hDynYdqJCu6xwDMM46ILtZ'; //Provide your application consumer key
$consumer_secret='dEAYaYVCE0jdi6pTTjbIYtndST3dWTvf6EZTxHZ1qK4rSSTRmT'; //Provide your application consumer secret
$oauth_token = '804421631263133696-oD6XIO2iEFwAo1SuHj1K7O0Pq6oribS'; //Provide your oAuth Token
$oauth_token_secret = 'evD2BGdvYfqXLw2Kah0PZU4u4HU0v0eBWdYx5muxTsymS'; //Provide your oAuth Token Secret

//2 - Include @abraham's PHP twitteroauth Library
require_once('twitteroauth/autoload.php');

//3 - Authentication
/* Create a TwitterOauth object with consumer/user tokens. */
$connection = new TwitterOAuth($consumer_key, $consumer_secret, $oauth_token, $oauth_token_secret);

//4 - Start Querying
$content = $connection->get("search/tweets", ["q" => "%23nuitinfo", "count" => 100]); // récupère les 100 derniers tweets avec le hashtag #nuitinfo
var_dump($content);
//$tweet = $connection->post("statuses/update", ["status" => "L'API Twitter marche enfin ! #nuitinfo"]); // tweet un message
?>