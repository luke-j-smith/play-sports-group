build-database-image:
	cd database && docker build -t psg-db:latest .

run-database: build-database-image
	cd database && docker run -d -p 3306:3306 --name psg-database psg-db:latest

run-application:
	cd video_information_service && mvn spring-boot:run

build-application:
	cd video_information_service && mvn package

clean:
	rm -rf video_information_service/target | true
	docker container rm -f psg-database | true
	docker rmi psg-db:latest | true
