FROM ivonet/payara:5.193.1
COPY ./artifact/MSASecondClient.war $DEPLOY_DIR
