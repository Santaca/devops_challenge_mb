from locust import HttpUser, TaskSet, between, task, constant

class MyTaskSet(TaskSet):
    @task
    def my_task(self):
        self.client.get("/sw")

class MyUser(HttpUser):
    tasks = [MyTaskSet]
    wait_time = between(0.5, 10)