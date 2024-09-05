import http.server
import socketserver
import requests
import json

PORT = 9090

EXTERNAL_API_BASE_URL = "https://swapi.dev/api/people/"

class MyHandler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        try:
            response = requests.get(EXTERNAL_API_BASE_URL)
            json_respo = response.json()

            results = json_respo.get("results")

            sorted_results = sorted(results, key=lambda x: x['name'])

            self.send_response(response.status_code)
            
            self.send_header("Content-type", response.headers.get('Content-Type', 'application/json'))
            self.end_headers()
            

            self.wfile.write(json.dumps(sorted_results).encode())
        except requests.RequestException as e:
            self.send_response(500)
            self.end_headers()
            error_message = f"Error al contactar con la API externa: {e}"
            self.wfile.write(error_message.encode())

with socketserver.TCPServer(("", PORT), MyHandler) as httpd:
    print(f"Servidor levantado en el puerto {PORT}")
    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        print("\nServidor detenido manualmente.")
    finally:
        httpd.server_close()
