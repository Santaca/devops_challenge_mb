from locust import HttpUser, TaskSet, between, task, constant

class MyTaskSet(TaskSet):
    @task
    def my_task(self):
        self.client.get("/")

class MyUser(HttpUser):
    tasks = [MyTaskSet]
    wait_time = between(0.5, 2.5)