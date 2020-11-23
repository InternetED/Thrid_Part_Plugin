# Thrid_Part_Plugin
Includes login, ad handle methods.


如果有需要使用到 AdMob 的功能，請於 strings 中設定以下程式碼：
```
<string name="admob_id" tools:ignore="MissingTranslation">${應用程式 ID}</string>
```


如果有需要使用到 Fb 登入的功能，請於 strings 中設定

# LoginManager


前言：如果有需要使用到 LoginManager，請務必遵守以下幾點：

1. 根據登入的平台設定對應的 ID，位置：res/values/strings 中
    - Google：login_google_serverId
    - Facebook：login_facebook_applicationId
    - Line：login_line_channelId