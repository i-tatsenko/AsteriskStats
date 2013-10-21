AsteriskStats
=============
This project is under development.

It is used to control expensive calls in easier way, that it can be done with common freePbx options.

To use this program you need to have settings.xml file in your ~/.AsteriskStats/ folder.
In future it will be possible to create file in program UI.
Now it is needed to create it mannualy with the following structure:
< ?xml version="1.0"?>
<settings>
	<sqlserver>
		<host>your_mysql_server</host>
		<user>mysql_user</user>
		<password>_mysql_user_password</password>
	</sqlserver>
</settings>
This file will be updated in future.

