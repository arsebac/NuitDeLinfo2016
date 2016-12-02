<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Last Tweets</title>


</head>

<body>

<!-- Une ou plusieurs balises HTML pour définir le contenu du document -->

<script src="jquery.js"></script>

<script src="mon-script.js"></script>

<a class="twitter-timeline" href="https://twitter.com/hashtag/migrant" data-widget-id="804488339332231168">Tweets sur
    #migrant</a>
<script>!function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/.test(d.location) ? 'http' : 'https';
        if (!d.getElementById(id)) {
            js = d.createElement(s);
            js.id = id;
            js.src = p + "://platform.twitter.com/widgets.js";
            fjs.parentNode.insertBefore(js, fjs);
        }
    }(document, "script", "twitter-wjs");</script>


<?php
require_once('twitter.php');
getTweets('#migrant', 30);

?>


</body>


</html>