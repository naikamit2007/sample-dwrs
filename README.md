### Deployment Architecture
![Application architecture](arch.JPG)

### Tech-stack
1. Terraform for OCI infrastructure provisioning
2. Ansible for application deployment
3. Dropwizard for microservice framework
4. Docker to bundle
5. TravisCi for Integration 

### Installation sequence
1. Push code to github repo
3. TravisCi builds the docker image and pushed to DockerHub
2. Deploy the network topology and application using the scripts in network

### Dropwizardexamples
How to start the Dropwizardexamples application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizard-examples-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

### Deploying the Module
We now need to initialize the directory with the module in it.  This makes the module aware of the OCI provider.  You can do this by running:

    terraform init

Now for the main attraction.  Let's make sure the plan looks good:

    terraform plan

If that's good, we can go ahead and apply the deploy:

    terraform apply

You'll need to enter `yes` when prompted.  Once complete, you'll see something like this:

### Grafana
Grafana UI will be accessible on `http://<monitor-host-ip>:3000`
