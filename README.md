https://github.com/TheKeyholdingCompany/tech-challenge-backend-dev

## Setup
To set up the Interstellar Route Planner, follow these steps:

1. Clone the repository to your local machine.
2. Set up DynamoDB v1.x on your computer.
   * Download DynamoDB local v1.x for free from [`this link'](https://d1ni2b6xgvw0s0.cloudfront.net/dynamodb_local_latest.zip)
   * After you download the archive, extract the contents and copy the extracted directory to a location of your choice.
   * To start DynamoDB on your computer, open a command prompt window, navigate to the directory where you extracted DynamoDBLocal.jar, and enter the following command.
`java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb` This will create a local database in the same directory as the JAR file.
   * Before you can access DynamoDB programmatically or through the AWS Command Line Interface (AWS CLI), you must configure your credentials to enable authorization for your applications.
   * You can use the `aws configure` command of the AWS CLI to set up credentials.

3. Run `create-local-db.sh` to create local db.
4. Run ApiApplication.java in IntellJ IDEA.

