ant
for i in `seq 1 $2`
do
	x-terminal-emulator -e java -Djava.security.policy=java.policy -jar server.jar -$1 $i
done
