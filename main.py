import http.server
import socketserver
import requests
import re

# Definimos el puerto
PORT = 9090

# URL base de la API externa
EXTERNAL_API_BASE_URL = "https://swapi.dev/api/"

class MyHandler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        # Extraemos el path de la solicitud
        path = self.path

        # Construimos la URL de la API externa
        url = f"{EXTERNAL_API_BASE_URL}{path}"
        
        try:
            # Hacemos la solicitud a la API externa
            response = requests.get(url)

            # Escribimos la respuesta en el servidor
            self.send_response(response.status_code)
            self.send_header("Content-type", response.headers.get('Content-Type', 'application/json'))
            self.end_headers()
            self.wfile.write(response.content)
        except requests.RequestException as e:
            # En caso de error en la solicitud a la API externa
            self.send_response(500)
            self.end_headers()
            self.wfile.write(f"Error al contactar con la API externa: {e}".encode())

# Levantamos el servidor
with socketserver.TCPServer(("", PORT), MyHandler) as httpd:
    print(f"Servidor levantado en el puerto {PORT}")
    httpd.serve_forever()
