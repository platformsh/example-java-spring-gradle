# Java Spring template for Platform.sh with Gradle

This project provides a starter kit for using Gradle to build a Java Spring application on Platform.sh.  It is only a basic Hello-World level example, but can be used as the basis for an actual project.

## Starting a new project

To start a new project based on this template, follow these 3 simple steps:

1. Clone this repository locally.  You may optionally remove the `origin` remote or remove the `.git` directory and re-init the project if you want a clean history.
 
2. Create a new project through the Platform.sh user interface and select "Import an existing project" when prompted.

3. Run the provided Git commands to add a Platform.sh remote and push the code to the Platform.sh repository.

That's it!  You now have a working "hello world" level project you can build on.

## Using as a reference

You can also use this repository as a reference for your own projects, and borrow whatever code is needed. 

The most important parts are the `.platform.app.yaml` file and the `.platform` directory, which define how a Platform.sh project will be configured.

Of particular note, Platform.sh provides information to applications via environment variables.  Java applications generally prefer to use system properties for defining runtime information.  The provided `platformsh.py` Python script provides a bridge between the Unix environment and Java system variables.  You can and should modify it as needed for your application and service relationships.  See the inline comments in that file.
