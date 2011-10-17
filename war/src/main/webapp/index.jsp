<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0014)about:internet -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<head>
  <title>Application SWF</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

  <style type="text/css" media="screen">
    html, body {
      height: 100%;
    }

    #flashContent {
      display: none;
    }
  </style>

  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
  <script type="text/javascript" src="js/swfobject.js"></script>
  <script type="text/javascript" src="js/application.js"></script>


  <script type="text/javascript">
    var swfVersionStr = "9.0.0";
    var xiSwfUrlStr = "${expressInstallSwf}";
    var flashvars1 = {userKey:"100500", outParam:"text1"};
    var flashvars2 = {userKey:"100501", outParam:"text2"};
    var flashvars3 = {userKey:"100502", outParam:"text3"};
    var params = {};
    params.quality = "high";
    params.allowscriptaccess = "sameDomain";
    params.allowfullscreen = "true";
    var attributes = {};
    attributes.id = "swf";
    attributes.name = "swf";
    attributes.align = "middle";

    swfobject.embedSWF(
        "swf-1.0.swf", "flashContent1",
        "0", "0",
        swfVersionStr, xiSwfUrlStr,
        flashvars1, params, attributes);

    swfobject.embedSWF(
        "swf-1.0.swf", "flashContent2",
        "0", "0",
        swfVersionStr, xiSwfUrlStr,
        flashvars2, params, attributes);

    swfobject.embedSWF(
        "swf-1.0.swf", "flashContent3",
        "0", "0",
        swfVersionStr, xiSwfUrlStr,
        flashvars3, params, attributes);
    swfobject.createCSS("#flashContent", "display:none;text-align:left;");
  </script>
</head>
<body>

<div id="flashContent1">
  <p>
    To view this page ensure that Adobe Flash Player version
    9.0.0 or greater is installed.
  </p>
  <script type="text/javascript">
    var pageHost = ((document.location.protocol == "https:") ? "https://" : "http://");
    document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='"
        + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>");
  </script>
</div>

<div id="flashContent2">
  <p>
    To view this page ensure that Adobe Flash Player version
    9.0.0 or greater is installed.
  </p>
  <script type="text/javascript">
    var pageHost = ((document.location.protocol == "https:") ? "https://" : "http://");
    document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='"
        + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>");
  </script>
</div>

<div id="flashContent3">
  <p>
    To view this page ensure that Adobe Flash Player version
    9.0.0 or greater is installed.
  </p>
  <script type="text/javascript">
    var pageHost = ((document.location.protocol == "https:") ? "https://" : "http://");
    document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='"
        + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>");
  </script>
</div>


<div>
  <textarea id="text1" rows="10" cols="45"></textarea>
  <textarea id="text2" rows="10" cols="45"></textarea>
  <textarea id="text3" rows="10" cols="45"></textarea>
</div>

</body>
</html>
