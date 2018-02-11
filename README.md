# Using Amazon CLI
## Prerequisites
* Java version: 8
* Must have Maven installed
* Must have AWS CLI installed
## Using helper scripts
1. Create a lambda function on AWS
```
./create test_lambda org.vap.demo.TestLambda
```
- arg1: Name of AWS lambda function to create
- arg2: Fully-qualified name of the class where the `handleRequest()` method resides.
2. Implement `handleRequest()` method.
3. Update the function code by running the `./update` script.
4. Run using `./run` and `./rates` scripts.
- Input is in `input.json` file
- The `output.txt` will contain the response from lambda function
## Deploy using AWS CLI
```
aws lambda update-function-code \
    --function-name arn:aws:lambda:eu-west-2:242867849850:function:test \
    --zip-file fileb://target/aws-lambda-play-1.0.0.jar
```
## Invoke using AWS CLI
```
aws lambda invoke \
    --function-name arn:aws:lambda:eu-west-2:242867849850:function:test \
    --payload file://input.json \
    --invocation-type RequestResponse \
    --region eu-west-2 \
    --log-type Tail \
    output.txt
```
