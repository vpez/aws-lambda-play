# Using Amazon CLI
## Prerequisites
* Java version: 8
* Must have Maven installed
* Must have AWS CLI installed
## Deploy
```
aws lambda update-function-code \
    --function-name arn:aws:lambda:eu-west-2:242867849850:function:Capitalize \
    --zip-file fileb://target/aws-lambda-play-1.0.0.jar
```
## Invoke
```
aws lambda invoke \
    --function-name arn:aws:lambda:eu-west-2:242867849850:function:Capitalize \
    --payload file://input.json \
    --invocation-type RequestResponse \
    --region eu-west-2 \
    --log-type Tail \
    output.txt
```
## Helper scripts
* To assist development and testing, just perform `./update && ./run`
* Input is in `input.json` file
* The `output.txt` will contain the response from lambda function
