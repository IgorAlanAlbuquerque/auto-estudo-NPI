import requests


# URL base da API
BASE_URL = "http://localhost:8080"

# Dados do login
login_data = {
    "email": "igorigor@alu.ufc.br",
    "senha": "123"
}


# Função para fazer o login e pegar o token
def login():
    response = requests.post(f"{BASE_URL}/login", json=login_data)
    if response.status_code == 200:
        token = response.text.split(" ")[1]  # Extrai o token da resposta
        print("Login bem-sucedido. Token JWT obtido.")
        return token
    else:
        print("Falha no login.")
        return None


# Função para cadastrar um novo usuário
def cadastrar_usuario(token):
    user_data = {
        "name": "Fulano",
        "email": "fulano@example.com",
        "password": "senha123",
        "ativo": True
    }
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.post(
        f"{BASE_URL}/usuarios",
        json=user_data,
        headers=headers)
    if response.status_code == 201:
        print("Usuário cadastrado com sucesso.")
        return response.json()  # Retorna o usuário cadastrado
    else:
        print("Erro ao cadastrar o usuário.")
        return None


# Função para buscar um usuário por ID
def buscar_usuario_por_id(token, user_id):
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.get(f"{BASE_URL}/usuarios/{user_id}", headers=headers)
    if response.status_code == 200:
        print("Usuário encontrado:", response.json())
    else:
        print("Erro ao buscar o usuário.")


# Função para atualizar um usuário
def atualizar_usuario(token, user_id):
    user_data = {
        "name": "Fulano Atualizado",
        "email": "fulano_atualizado@example.com",
        "password": "nova_senha123",
        "ativo": False
    }
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.put(
        f"{BASE_URL}/usuarios/{user_id}",
        json=user_data,
        headers=headers)
    if response.status_code == 200:
        print("Usuário atualizado com sucesso.")
    else:
        print("Erro ao atualizar o usuário.")


# Função para listar todos os usuários
def listar_usuarios(token):
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.get(f"{BASE_URL}/usuarios", headers=headers)
    if response.status_code == 200:
        print("Lista de usuários:", response.json())
    else:
        print("Erro ao listar os usuários.")


# Função para deletar um usuário
def deletar_usuario(token, user_id):
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.delete(
        f"{BASE_URL}/usuarios/{user_id}",
        headers=headers)
    if response.status_code == 204:
        print("Usuário deletado com sucesso.")
    else:
        print("Erro ao deletar o usuário.")


# Função para cadastrar um curso
def cadastrar_curso(token):
    curso_data = {
        "name": "Curso de Computação",
        "sigla": "CC",
        "turno": "MANHA"
    }
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.post(
        f"{BASE_URL}/cursos",
        json=curso_data,
        headers=headers)
    if response.status_code == 200:
        print("Curso cadastrado com sucesso.")
        return response.json()  # Retorna o curso cadastrado
    else:
        print("Erro ao cadastrar o curso:", response.status_code)
        print(response.text)


# Função para buscar um curso por ID
def buscar_curso_por_id(token, curso_id):
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.get(f"{BASE_URL}/cursos/{curso_id}", headers=headers)
    if response.status_code == 200:
        print("Curso encontrado:", response.json())
    else:
        print("Erro ao buscar o curso.")


# Função para atualizar um curso
def atualizar_curso(token, curso_id):
    curso_data = {
        "name": "Curso de Engenharia de Computação",
        "sigla": "EC",
        "turno": "TARDE"
    }
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.put(
        f"{BASE_URL}/cursos/{curso_id}",
        json=curso_data,
        headers=headers)
    if response.status_code == 200:
        print("Curso atualizado com sucesso.")
    else:
        print("Erro ao atualizar o curso.")


# Função para listar todos os cursos
def listar_cursos(token):
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.get(f"{BASE_URL}/cursos", headers=headers)
    if response.status_code == 200:
        print("Lista de cursos:", response.json())
    else:
        print("Erro ao listar os cursos.")


# Função para associar um usuário a um curso
def associar_usuario_a_curso(token, user_id, curso_id):
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.post(
        f"{BASE_URL}/usuarios/{user_id}/curso/{curso_id}",
        headers=headers)
    if response.status_code == 200:
        print(f"Usuário {user_id} associado ao curso {curso_id}.")
    else:
        print("Erro ao associar o usuário ao curso.")


# Função para excluir um curso
def excluir_curso(token, curso_id):
    headers = {"Authorization": f"Bearer {token}"}
    response = requests.delete(
        f"{BASE_URL}/cursos/{curso_id}",
        headers=headers)
    if response.status_code == 204:
        print("Curso excluído com sucesso.")
    else:
        print("Erro ao excluir o curso.")


# TODO Rename this here and in `main`
def executar_testes_usuario(user, token):
    result = user['id']
    buscar_usuario_por_id(token, result)
    atualizar_usuario(token, result)
    listar_usuarios(token)
    deletar_usuario(token, result)
    listar_usuarios(token)


# TODO Rename this here and in `main`
def executar_testes_curso(curso, token, user_id):
    curso_id = curso['id']
    buscar_curso_por_id(token, curso_id)
    atualizar_curso(token, curso_id)
    listar_cursos(token)
    associar_usuario_a_curso(token, user_id, curso_id)
    listar_usuarios(token)
    excluir_curso(token, curso_id)
    listar_cursos(token)


# Main: Executando os testes
def main():
    if not (token := login()):
        return
    if user := cadastrar_usuario(token):
        executar_testes_usuario(user, token)
    if curso := cadastrar_curso(token):
        executar_testes_curso(curso, token, 1)


if __name__ == "__main__":
    main()
